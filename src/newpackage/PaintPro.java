/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newpackage;

import java.applet.Applet;
import java.awt.BasicStroke;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.Transparency;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Vector;

/**
 *
 * @author Aya
 */
public class PaintPro extends Applet {

    /**
     * Initialization method that will be called after the applet is loaded into
     * the browser.
     */
    class ShapePoint {

        int x;
        int y;
        private Color shapeColor;
        int order;

        public ShapePoint() {
        }

        public ShapePoint(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    class RectShape {

        ShapePoint startPoint;
        ShapePoint endPoint;
        Color shapeColor;
        int fill_Rect;
        int dott_Rect;
        int order;

        public RectShape() {
            startPoint = new ShapePoint();
            endPoint = new ShapePoint();
        }

        public RectShape(int x1, int y1, int x2, int y2) {
            startPoint = new ShapePoint(x1, y1);
            endPoint = new ShapePoint(x2, y2);
        }
    }

    class OvalShape {

        ShapePoint startPoint;
        ShapePoint endPoint;
        Color shapeColor;
        int fill_Oval;
        int dott_Oval;
        int order;

        public OvalShape() {
            startPoint = new ShapePoint();
            endPoint = new ShapePoint();
        }

        public OvalShape(int x1, int y1, int x2, int y2) {
            startPoint = new ShapePoint(x1, y1);
            endPoint = new ShapePoint(x2, y2);
        }
    }

    class LineShape {

        ShapePoint startPoint;
        ShapePoint endPoint;
        Color shapeColor;
        int dott_Line;
        int order;

        public LineShape() {
            startPoint = new ShapePoint();
            endPoint = new ShapePoint();
        }

        public LineShape(int x1, int y1, int x2, int y2) {
            startPoint = new ShapePoint(x1, y1);
            endPoint = new ShapePoint(x2, y2);
        }

    }

    Vector<RectShape> rectArr = new Vector<>();
    Vector<LineShape> lineArr = new Vector<>();
    Vector<OvalShape> ovalArr = new Vector<>();
    Vector<RectShape> eraserArr = new Vector<>();
    Vector allShapesArr = new Vector();

    Vector<ShapePoint> freeHand = new Vector<>();
    Vector arr = new Vector<>();
    Color shapeColor = Color.BLACK;

    RectShape rect_shape = new RectShape();
    LineShape Line_shape = new LineShape();
    OvalShape oval_shape = new OvalShape();
    RectShape rect_eraser = new RectShape();
    RectShape rectE = new RectShape();
    ShapePoint points = new ShapePoint();

    int drawShape = 0;
    int fillCheck = 0;
    int dottCheck = 0;
    int freeLine = 0;
    int shapesOrder = 0;
    int x1;
    int y1;
    int x2;
    int y2;
    int eraser_x;
    int eraser_y;

    @Override
    public void init() {
        // TODO start asynchronous download of heavy resource

        setSize(700, 500);
        Button greenBtn = new Button("Green");
        greenBtn.setBackground(Color.GREEN);
        Button blueBtn = new Button("Blue");
        blueBtn.setBackground(Color.BLUE);
        Button redBtn = new Button("Red");
        redBtn.setBackground(Color.RED);
        Button rectBtn = new Button("Rect");
        Button ovelBtn = new Button("Ovel");
        Button lineBtn = new Button("Line");
        Button freeHandBtn = new Button("Free Hand");
        Button ereaseBtn = new Button("Ereaser");
        Button ereaseAllBtn = new Button("Erease All");
        Button undoBtn = new Button("Undo");
        Checkbox filledCheck = new Checkbox("Filled");
        Checkbox dottedCheck = new Checkbox("Dotted");

        add(greenBtn);
        add(blueBtn);
        add(redBtn);
        add(rectBtn);
        add(ovelBtn);
        add(lineBtn);
        add(freeHandBtn);
        add(filledCheck);
        add(dottedCheck);
        add(ereaseBtn);
        add(ereaseAllBtn);
        add(undoBtn);

        /////eventlisenter
        greenBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                shapeColor = Color.GREEN;
            }
        });

        blueBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

                shapeColor = Color.blue;
            }
        });

        redBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                shapeColor = Color.RED;
            }
        });

        rectBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                drawShape = 1;
            }
        });

        ovelBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                drawShape = 2;
            }
        });

        lineBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                drawShape = 3;
            }
        });

        filledCheck.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    fillCheck = 1;
                } else {
                    fillCheck = 0;
                }
            }
        });
        dottedCheck.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    dottCheck = 1;
                } else {
                    dottCheck = 0;
                }
            }
        });

        ereaseAllBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                rectArr.removeAllElements();
                ovalArr.removeAllElements();
                lineArr.removeAllElements();
                freeHand.removeAllElements();
                eraserArr.removeAllElements();
                allShapesArr.removeAllElements();
                repaint();
            }

        });

        ereaseBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                drawShape = 5;
                rectE = new RectShape();
            }
        });

        undoBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

//                for (int i = 0; i < freeHand.size(); i++) {
//                    System.out.println(freeHand.get(i));
//                }
                int deletedShape = 0;
                if (allShapesArr.size() > 0) {

                    deletedShape = (int) allShapesArr.remove(allShapesArr.size() - 1);
                    System.out.println("delete : " + deletedShape);
                }

                if (deletedShape == 1) {
                    rectArr.remove(rectArr.size() - 1);
                } else if (deletedShape == 2) {
                    ovalArr.remove(ovalArr.size() - 1);
                } else if (deletedShape == 3) {
                    lineArr.remove(lineArr.size() - 1);
                } else if (deletedShape == 4) {
                    arr.remove(arr.size() - 1);
                } else {
                    System.out.println("No shapes in app!! ");
                }
                repaint();
            }

        });

        freeHandBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                drawShape = 4;
                freeLine = 1;
            }
        });

        this.addMouseMotionListener(new MouseMotionListener() {

            @Override
            public void mouseDragged(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

                x2 = e.getX();
                y2 = e.getY();
                if (drawShape == 1) {
                    rect_shape.endPoint.x = x2;
                    rect_shape.endPoint.y = y2;
                    rect_shape.shapeColor = shapeColor;
                    rect_shape.fill_Rect = fillCheck;
                    rect_shape.dott_Rect = dottCheck;
                } else if (drawShape == 2) {
                    oval_shape.endPoint.x = x2;
                    oval_shape.endPoint.y = y2;
                    oval_shape.shapeColor = shapeColor;
                    oval_shape.fill_Oval = fillCheck;
                    oval_shape.dott_Oval = dottCheck;
                } else if (drawShape == 3) {
                    Line_shape.endPoint.x = x2;
                    Line_shape.endPoint.y = y2;
                    Line_shape.shapeColor = shapeColor;
                    Line_shape.dott_Line = dottCheck;
                } else if (drawShape == 4) {
                    allShapesArr.add(4);
                    ShapePoint points = new ShapePoint();
                    points.x = e.getX();
                    points.y = e.getY();
                    points.shapeColor = shapeColor;
                    points.order = ++shapesOrder;
                    freeHand.add(points);
      
                } else if (drawShape == 5) {
                    rect_eraser.startPoint.x = e.getX();
                    rect_eraser.startPoint.y = e.getY();
                    RectShape rectE = new RectShape();
                    rectE.startPoint.x = e.getX();
                    rectE.startPoint.y = e.getY();
                    rectE.order = ++shapesOrder;
                    eraserArr.add(rectE);
                }
                repaint();
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                if (drawShape == 5) {
                    rect_eraser.startPoint.x = e.getX();
                    rect_eraser.startPoint.y = e.getY();
                    repaint();

                }
            }

        });
        this.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mousePressed(MouseEvent e) {
                // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

                x1 = e.getX();
                y1 = e.getY();

                if (drawShape == 1) {
                    rect_shape = new RectShape();
                    rect_shape.startPoint.x = x1;
                    rect_shape.startPoint.y = y1;
                    rect_shape.shapeColor = shapeColor;
                    rect_shape.fill_Rect = fillCheck;
                    rect_shape.dott_Rect = dottCheck;
                    rect_shape.order = ++shapesOrder;
                    rectArr.add(rect_shape);

                } else if (drawShape == 2) {
                    oval_shape = new OvalShape();
                    oval_shape.startPoint.x = x1;
                    oval_shape.startPoint.y = y1;
                    oval_shape.shapeColor = shapeColor;
                    oval_shape.fill_Oval = fillCheck;
                    oval_shape.dott_Oval = fillCheck;
                    oval_shape.order = ++shapesOrder;
                    ovalArr.add(oval_shape);
                } else if (drawShape == 3) {
                    Line_shape = new LineShape();
                    Line_shape.startPoint.x = x1;
                    Line_shape.startPoint.y = y1;
                    Line_shape.shapeColor = shapeColor;
                    Line_shape.dott_Line = dottCheck;
                    Line_shape.order = ++shapesOrder;
                    lineArr.add(Line_shape);
                } else if (drawShape == 5) {
                    rectE.startPoint.x = e.getX();
                    rectE.startPoint.y = e.getY();

                } else if (drawShape == 4) {
                    points.x = e.getX();
                    points.y = e.getY();
                    points.shapeColor = shapeColor;
                    points.order = ++shapesOrder;
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                x2 = e.getX();
                y2 = e.getY();
                if (drawShape == 1) {
                    rect_shape.endPoint.x = x2;
                    rect_shape.endPoint.y = y2;
                    rect_shape.shapeColor = shapeColor;
                    rect_shape.fill_Rect = fillCheck;
                    rect_shape.dott_Rect = dottCheck;
//                    rectArr.add(rect_shape);
                    allShapesArr.add(1);
                } else if (drawShape == 2) {
                    oval_shape.endPoint.x = x2;
                    oval_shape.endPoint.y = y2;
                    oval_shape.shapeColor = shapeColor;
                    oval_shape.fill_Oval = fillCheck;
                    oval_shape.dott_Oval = dottCheck;
//                    ovalArr.add(oval_shape);
                    allShapesArr.add(2);
                } else if (drawShape == 3) {
                    Line_shape.endPoint.x = x2;
                    Line_shape.endPoint.y = y2;
                    Line_shape.shapeColor = shapeColor;
                    Line_shape.dott_Line = dottCheck;
                    allShapesArr.add(3);
//                    lineArr.add(Line_shape);
                } else if (drawShape == 5) {
                    rectE.startPoint.x = e.getX();
                    rectE.startPoint.y = e.getY();
                    //eraserArr.add(rectE);
                } else if (drawShape == 4) {
                    points.shapeColor = shapeColor;
                    points.x = e.getX();
                    points.y = e.getY();
                   
                }
                repaint();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseExited(MouseEvent e) {
                //     throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });

    }

    // TODO overwrite start(), stop() and destroy() methods
    @Override
    public void paint(Graphics g) {
        super.paint(g); //To change body of generated methods, choose Tools | Templates.

        for (int a = 1; a <= shapesOrder; a++) {
            //rectangles
            for (int i = 0; i < rectArr.size(); i++) {
                if (rectArr.get(i).order == a) {
                    draw_Rectangle(g, Math.min(rectArr.get(i).startPoint.x, rectArr.get(i).endPoint.x),
                            Math.min(rectArr.get(i).startPoint.y, rectArr.get(i).endPoint.y),
                            Math.abs(rectArr.get(i).startPoint.x - rectArr.get(i).endPoint.x),
                            Math.abs(rectArr.get(i).startPoint.y - rectArr.get(i).endPoint.y),
                            rectArr.get(i).shapeColor, rectArr.get(i).fill_Rect, rectArr.get(i).dott_Rect);
                }

            }

            ///oval
            for (int i = 0; i < ovalArr.size(); i++) {
                if (ovalArr.get(i).order == a) {
                    draw_Oval(g, Math.min(ovalArr.get(i).startPoint.x, ovalArr.get(i).endPoint.x),
                            Math.min(ovalArr.get(i).startPoint.y, ovalArr.get(i).endPoint.y),
                            Math.abs(ovalArr.get(i).startPoint.x - ovalArr.get(i).endPoint.x),
                            Math.abs(ovalArr.get(i).startPoint.y - ovalArr.get(i).endPoint.y),
                            ovalArr.get(i).shapeColor, ovalArr.get(i).fill_Oval, ovalArr.get(i).dott_Oval);
                }
            }

            //lines
            for (int i = 0; i < lineArr.size(); i++) {
                if (lineArr.get(i).order == a) {
                    draw_Line(g, lineArr.get(i).startPoint.x, lineArr.get(i).startPoint.y, lineArr.get(i).endPoint.x, lineArr.get(i).endPoint.y, lineArr.get(i).shapeColor, lineArr.get(i).dott_Line);
                }

            }
            //eraser
            if (!eraserArr.isEmpty()) {
                for (int i = 0; i < eraserArr.size(); i++) {
                    if (eraserArr.get(i).order == a) {
                        g.clearRect(eraserArr.get(i).startPoint.x, eraserArr.get(i).startPoint.y, 50, 50);
                        //draw_Rectangle(g, eraserArr.get(i).startPoint.x, eraserArr.get(i).startPoint.y, 50, 50, Color.white, 1, 0);
                    }
                }
            }

            //free hand
            if (!freeHand.isEmpty()) {
                for (int i = 1; i < freeHand.size(); i++) {
                    if (freeHand.get(i).order == a) {
                        g.setColor(freeHand.get(i).shapeColor);
                        g.fillOval(freeHand.get(i).x, freeHand.get(i).y, 5, 5);
                    }
                }
            }

        }

        if (drawShape == 5) {
            draw_Rectangle(g, rect_eraser.startPoint.x, rect_eraser.startPoint.y, 50, 50, Color.BLACK, 1, 0);
        }
    }

    public void draw_Line(Graphics g, int x1, int y1, int x2, int y2, Color c, int dotted) {
        g.setColor(c);
        Graphics2D g2d = (Graphics2D) g.create();
        Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{5}, 0);
        g2d.setStroke(dashed);

        if (dotted == 1) {
            g2d.drawLine(x1, y1, x2, y2);
        } else {
            g.drawLine(x1, y1, x2, y2);
        }

    }

    public void draw_Rectangle(Graphics g, int x1, int y1, int x2, int y2, Color c, int filled, int dotted) {

        g.setColor(c);
        Graphics2D g2d = (Graphics2D) g.create();
        Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{5}, 0);
        g2d.setStroke(dashed);
        if (filled == 1) {
            if (dotted == 1) {
                g2d.fillRect(x1, y1, x2, y2);
            } else {
                g.fillRect(x1, y1, x2, y2);
            }
        } else {
            if (dotted == 1) {
                g2d.drawRect(x1, y1, x2, y2);

            } else {
                g.drawRect(x1, y1, x2, y2);
            }
        }
    }

    public void draw_Oval(Graphics g, int x1, int y1, int x2, int y2, Color c, int filled, int dotted) {

        g.setColor(c);
        Graphics2D g2d = (Graphics2D) g.create();
        Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{5}, 0);
        g2d.setStroke(dashed);
        if (filled == 1) {
            if (dotted == 1) {

                g2d.fillOval(x1, y1, x2, y2);
            } else {
                g.fillOval(x1, y1, x2, y2);
            }
        } else {
            if (dotted == 1) {
                g2d.drawOval(x1, y1, x2, y2);
            } else {
                g.drawOval(x1, y1, x2, y2);
            }

        }
    }

}
