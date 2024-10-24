package org.example;

import org.example.impl.MazmorraLoad;
import org.example.impl.MazmorraLog;
import org.example.impl.MazmorraTree;
import org.example.impl.moves.MazmorraMove;
import org.example.impl.moves.MazmorraMoveListener;
import org.example.model.Dungeon;
import org.example.model.Room;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JPanel mainPanel = new JPanel(new BorderLayout());
        JFrame frame = new JFrame("Mazmorra");
        frame.add(mainPanel);
        frame.setVisible(true);
        frame.setSize(1280, 720);
        frame.setTitle("Mazmorras");
        frame.setResizable(false);
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Opciones");
        JMenuItem menuItemSalir = new JMenuItem("Salir");
        JMenuItem menuItemLoad = new JMenuItem("Load");
        menu.add(menuItemSalir);
        menu.add(menuItemLoad);
        menuBar.add(menu);
        frame.setJMenuBar(menuBar);
        MazmorraLoad mLoad = new MazmorraLoad();
        final MazmorraLog mLog = new MazmorraLog();
        MazmorraTree mTree = new MazmorraTree();
        MazmorraMoveListener listener = new MazmorraMoveListener() {
            public void roomUpdated(Room room) {
                mLog.addLogMessage("Has ido a: " + room.getId() + "\n");
            }
        };
        MazmorraMove mMove = new MazmorraMove(listener);
        JSplitPane splitPaneVertical = new JSplitPane(0);
        splitPaneVertical.setTopComponent((Component)mMove);
        splitPaneVertical.setBottomComponent((Component)mLog);
        splitPaneVertical.setDividerLocation(300);
        JSplitPane splitPaneHorizontal = new JSplitPane(1);
        splitPaneHorizontal.setLeftComponent((Component)mTree);
        splitPaneHorizontal.setRightComponent(splitPaneVertical);
        splitPaneHorizontal.setDividerLocation(200);
        mainPanel.add(splitPaneHorizontal, "Center");
        mainPanel.updateUI();
        menuItemSalir.addActionListener(e -> System.exit(0));
        menuItemLoad.addActionListener(e -> {
            mLog.clearLog();
            mLoad.loadXMLFile();
            Dungeon dungeon = mLoad.getDungeon();
            List<Room> rooms = dungeon.getRooms();
            mMove.setRooms(rooms);
            mMove.loadRoom(rooms.get(0));
            mLog.addLogMessage("Comienza tu aventura, esten la habitaci" + ((Room)rooms.get(0)).getId() + "\n");
            splitPaneHorizontal.setLeftComponent(mTree.createJTree(dungeon));
            mainPanel.updateUI();
        });
    }
}
