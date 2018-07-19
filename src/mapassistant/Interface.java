/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapassistant;

import com.bulenkov.darcula.DarculaLaf;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicLookAndFeel;

/**
 *
 * @author Andr�s
 */
public class Interface extends javax.swing.JFrame {

    private static Interface interface_frame;
    PanAndZoomCanvas canvas;
    public AffineTransform addedTransform;   // the current pan and zoom transform
    // saves the initial transform at the beginning of the pan interaction
    public AffineTransform initialTransform;
    Point2D pointOnMap; // storage for a transformed mouse point
    BufferedImage currentMapImage;

    /**
     * Creates new form Interface
     */
    public Interface() {
        initComponents();

        canvas = panAndZoomCanvas1;
        canvas.setIn(this);

        PanningHandler panner = new PanningHandler();
        canvas.addMouseListener(panner);
        canvas.addMouseMotionListener(panner);
        canvas.addMouseWheelListener(panner);
        canvas.setBorder(BorderFactory.createLineBorder(Color.black));

        JSlider zoomSlider = jSlider1;
        zoomSlider.setMajorTickSpacing(25);
        zoomSlider.setMinorTickSpacing(25);
        zoomSlider.setPaintTicks(true);
        zoomSlider.setPaintLabels(true);
        zoomSlider.addChangeListener(new ScaleHandler());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jSlider1 = new javax.swing.JSlider();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        panAndZoomCanvas1 = new mapassistant.PanAndZoomCanvas();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Realm Map Assistant");

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mapassistant/images/assistant.png"))); // NOI18N
        jLabel1.setText("Realm Slayers");

        jLabel7.setText("Zoom");

        jSlider1.setMaximum(200);
        jSlider1.setMinimum(50);
        jSlider1.setSnapToTicks(true);
        jSlider1.setValue(100);

        jTextField1.setText("Paste the current Raid Map's URL here");
        jTextField1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField1MouseClicked(evt);
            }
        });
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jButton1.setText("Go!");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        panAndZoomCanvas1.setName(""); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panAndZoomCanvas1, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel7)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(5, 5, 5)
                        .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panAndZoomCanvas1, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            URL url = new URL(jTextField1.getText());
            URLConnection hc = url.openConnection();
            hc.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2");

            BufferedImage map = ImageIO.read(hc.getInputStream());

            currentMapImage = map;

            panAndZoomCanvas1.translateX = 0;
            panAndZoomCanvas1.translateY = 0;
            panAndZoomCanvas1.repaint();
        } catch (MalformedURLException ex) {
            JOptionPane.showMessageDialog(null, "Not a valid URL!", "Error!", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed

    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField1MouseClicked
        jTextField1.requestFocusInWindow();
        jTextField1.selectAll();
        revalidate();
        repaint();
    }//GEN-LAST:event_jTextField1MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JSlider jSlider1;
    private javax.swing.JTextField jTextField1;
    private mapassistant.PanAndZoomCanvas panAndZoomCanvas1;
    // End of variables declaration//GEN-END:variables

    class PanningHandler implements MouseListener,
            MouseMotionListener, MouseWheelListener {

        double referenceX;
        double referenceY;
        private boolean mousePressed;
        private Point2D pointOnMapBeforeZoom;

        @Override
        public void mousePressed(MouseEvent e) {
            mousePressed = true;
            
            try {
                pointOnMapBeforeZoom = addedTransform.inverseTransform(e.getPoint(), null);
                pointOnMap = addedTransform.inverseTransform(e.getPoint(), null);

            } catch (NoninvertibleTransformException te) {
                System.out.println(te);
            }

            referenceX = pointOnMap.getX();
            referenceY = pointOnMap.getY();
            initialTransform = addedTransform;
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            try {
                pointOnMap = initialTransform.inverseTransform(e.getPoint(), null);
            } catch (NoninvertibleTransformException te) {
                System.out.println(te);
            }

            double deltaX = pointOnMap.getX() - referenceX;
            double deltaY = pointOnMap.getY() - referenceY;

            referenceX = pointOnMap.getX();
            referenceY = pointOnMap.getY();

            if (currentMapImage != null) {
                canvas.translateX += deltaX;
                canvas.translateY += deltaY;
            }

            canvas.repaint();
        }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }

        @Override
        public void mouseMoved(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            mousePressed = false;
        }

        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
            int notches = e.getWheelRotation();

            if (e.getScrollType() == MouseWheelEvent.WHEEL_UNIT_SCROLL) {
                if (notches < 0) {

                    jSlider1.setValue(jSlider1.getValue() + jSlider1.getMinorTickSpacing());

                } else if (notches > 0) {
                    jSlider1.setValue(jSlider1.getValue() - jSlider1.getMinorTickSpacing());

                }
            } else {

            }

            if (notches != 0) {

                try {
                    Point2D pointAfterZoom = addedTransform.inverseTransform(e.getPoint(), null);

                    if (mousePressed) {

                        double deltaX = pointAfterZoom.getX() - pointOnMapBeforeZoom.getX();
                        double deltaY = pointAfterZoom.getY() - pointOnMapBeforeZoom.getY();

                        canvas.translateX += deltaX;
                        canvas.translateY += deltaY;
                    }

                    pointOnMap = addedTransform.inverseTransform(e.getPoint(), null);

                } catch (NoninvertibleTransformException te) {
                    System.out.println(te);
                }
                referenceX = pointOnMap.getX();
                referenceY = pointOnMap.getY();
                initialTransform = addedTransform;
                canvas.repaint();
            }

        }

    }

    class ScaleHandler implements ChangeListener {

        @Override
        public void stateChanged(ChangeEvent e) {
            JSlider slider = (JSlider) e.getSource();
            int zoomPercent = slider.getValue();

            canvas.scale = zoomPercent / 100.0;
            canvas.repaint();
            canvas.applyAddedTransform();
        }
    }

    public static void main(String[] args) {

        BasicLookAndFeel darcula = new DarculaLaf();
        try {
            UIManager.setLookAndFeel(darcula);
        } catch (Exception e) {
            e.printStackTrace();
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                interface_frame = new Interface();
                interface_frame.setVisible(true);
            }
        });

    }
}