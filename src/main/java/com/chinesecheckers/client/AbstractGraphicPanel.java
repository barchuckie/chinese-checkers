package com.chinesecheckers.client;

import javax.swing.*;
import java.io.PrintWriter;

public abstract class AbstractGraphicPanel extends JPanel {
    abstract void setActive(Field field);
    abstract void setMyTurn();
    abstract void setNotMyTurn();
    abstract void disactive();

}
