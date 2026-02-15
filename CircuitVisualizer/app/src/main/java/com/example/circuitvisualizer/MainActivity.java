package com.example.circuitvisualization;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the custom view to draw the circuit components
        setContentView(new CircuitView(this));
    }

    // CustomView to draw electronic components in the circuit
    private static class CircuitView extends View {

        private final Paint paint;

        public CircuitView(Context context) {
            super(context);
            // Initialize Paint object for drawing
            paint = new Paint();
            paint.setAntiAlias(true); // Smoother edges
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            // Define starting positions and spacing
            int startX = 200;
            int startY = 300;
            int spacing = 300; // Vertical spacing between components

            // Draw Resistor
            int resistorEndX = drawResistor(canvas, startX, startY, paint);

            // Draw Capacitor below the Resistor
            int capacitorEndX = drawCapacitor(canvas, startX, startY + spacing, paint);

            // Draw LED below the Capacitor
            int ledEndX = drawLED(canvas, startX, startY + 2 * spacing, paint);

            // Draw arrows connecting the components
            drawArrow(canvas, resistorEndX, startY, capacitorEndX, startY + spacing, paint);  // Resistor to Capacitor
            drawArrow(canvas, capacitorEndX, startY + spacing, ledEndX, startY + 2 * spacing, paint);  // Capacitor to LED
        }

        // Resistor: Zigzag line with 4 peaks
        private int drawResistor(Canvas canvas, int x, int y, Paint paint) {
            final int PEAKS = 6;
            final int zigzagWidth = 40;
            final int zigzagHeight = 40;

            paint.setColor(Color.BLACK);
            paint.setStrokeWidth(8);
            paint.setStyle(Paint.Style.STROKE);

            // Left horizontal connector line
            int zigzagStartX = x;
            int zigzagEndX = x + PEAKS * zigzagWidth;
            canvas.drawLine(x - 50, y, zigzagStartX, y, paint);

            // Draw zigzag path
            Path path = new Path();
            path.moveTo(zigzagStartX, y);

            for (int i = 0; i < PEAKS; i++) {
                int xOffset = zigzagStartX + (i + 1) * zigzagWidth;
                int yOffset = (i % 2 == 0) ? y - zigzagHeight : y + zigzagHeight;
                path.lineTo(xOffset, yOffset);
            }
            path.lineTo(zigzagEndX + zigzagWidth, y);
            canvas.drawPath(path, paint);

            // Right horizontal connector line
            canvas.drawLine(zigzagEndX + zigzagWidth, y, zigzagEndX + zigzagWidth + 50, y, paint);

            // Label
            paint.setStyle(Paint.Style.FILL);
            paint.setTextSize(50);
            canvas.drawText("Resistor", x + (PEAKS * zigzagWidth) / 2 - 60, y - zigzagHeight - 40, paint);

            return zigzagEndX + zigzagWidth + 50;
        }

        // Capacitor: Two parallel vertical lines
        private int drawCapacitor(Canvas canvas, int x, int y, Paint paint) {
            final int lineLength = 80;
            final int gap = 30;

            paint.setColor(Color.BLACK);
            paint.setStrokeWidth(8);
            paint.setStyle(Paint.Style.STROKE);

            // Left connector line
            canvas.drawLine(x - 50, y, x, y, paint);

            paint.setStrokeWidth(14);

            // Vertical capacitor plates
            canvas.drawLine(x, y - lineLength / 2, x, y + lineLength / 2, paint);
            canvas.drawLine(x + gap, y - lineLength / 2, x + gap, y + lineLength / 2, paint);

            paint.setStrokeWidth(8);

            // Right connector line
            canvas.drawLine(x + gap, y, x + gap + 50, y, paint);

            // Label
            paint.setStyle(Paint.Style.FILL);
            paint.setTextSize(50);
            canvas.drawText("Capacitor", x + (gap / 2) - (paint.measureText("Capacitor") / 2), y - lineLength / 2 - 20, paint);

            return x + gap + 50;
        }

        // LED: Triangle with light arrows
        private int drawLED(Canvas canvas, int x, int y, Paint paint) {
            int offsetX = 10;

            paint.setColor(Color.BLACK);
            paint.setStrokeWidth(8);
            paint.setStyle(Paint.Style.STROKE);

            // Horizontal lines
            canvas.drawLine(x - 120 + offsetX, y, x + offsetX, y, paint);
            canvas.drawLine(x + offsetX, y, x + 50 + offsetX, y, paint);

            // Triangle (Anode)
            paint.setStyle(Paint.Style.FILL);
            Path trianglePath = new Path();
            trianglePath.moveTo(x + offsetX, y);
            trianglePath.lineTo(x - 70 + offsetX, y - 50);
            trianglePath.lineTo(x - 70 + offsetX, y + 50);
            trianglePath.close();
            canvas.drawPath(trianglePath, paint);

            // Cathode line
            paint.setStyle(Paint.Style.STROKE);
            canvas.drawLine(x + offsetX, y - 50, x + offsetX, y + 50, paint);

            // Light emission arrows
            drawLightArrow(canvas, x - 60 + offsetX, y - 70, 30, -45, paint);
            drawLightArrow(canvas, x - 30 + offsetX, y - 50, 30, -45, paint);

            // Label
            paint.setStyle(Paint.Style.FILL);
            paint.setTextSize(40);
            canvas.drawText("LED", x - 100 + offsetX, y + 100, paint);

            return x + 50 + offsetX;
        }

        // Draw light emission arrows
        private void drawLightArrow(Canvas canvas, int startX, int startY, int length, int angle, Paint paint) {
            double radians = Math.toRadians(angle);
            float endX = startX + (float) (length * Math.cos(radians));
            float endY = startY + (float) (length * Math.sin(radians));

            paint.setStrokeWidth(6);
            canvas.drawLine(startX, startY, endX, endY, paint);
            drawArrowHead(canvas, endX, endY, angle, 30, 15, paint);
        }

        private void drawArrowHead(Canvas canvas, float tipX, float tipY, float angle, float arrowHeadAngle, float size, Paint paint) {
            double radians1 = Math.toRadians(angle + arrowHeadAngle);
            double radians2 = Math.toRadians(angle - arrowHeadAngle);

            float endX1 = tipX - (float) (size * Math.cos(radians1));
            float endY1 = tipY - (float) (size * Math.sin(radians1));
            float endX2 = tipX - (float) (size * Math.cos(radians2));
            float endY2 = tipY - (float) (size * Math.sin(radians2));

            canvas.drawLine(tipX, tipY, endX1, endY1, paint);
            canvas.drawLine(tipX, tipY, endX2, endY2, paint);
        }

        // Draw arrow between components
        private void drawArrow(Canvas canvas, int startX, int startY, int endX, int endY, Paint paint) {
            // Draw a line between the two components
            canvas.drawLine(startX, startY, endX, endY, paint);

            // Draw the arrowhead at the end of the line
            float angle = (float) Math.toDegrees(Math.atan2(endY - startY, endX - startX));
            drawArrowHead(canvas, endX, endY, angle, 30, 15, paint);
        }
    }
}
