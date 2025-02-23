package com.example.myfirstapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SensorGraphView extends View {
    private final Paint valuePaint = new Paint();
    private final Paint meanPaint = new Paint();
    private final Paint stdDevPaint = new Paint();
    private final Paint axisPaint = new Paint();
    private final Paint textPaint = new Paint();

    private final List<Float> sensorValues = new ArrayList<>();
    private final List<Float> runningMean = new ArrayList<>();
    private final List<Float> runningStdDev = new ArrayList<>();

    private static final int GRID_SPACING = 100; // Distance between grid lines


    private final int maxPoints = 100;///mum points on the graph

    public SensorGraphView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        valuePaint.setColor(Color.GREEN);
        valuePaint.setStrokeWidth(5f);
        meanPaint.setColor(Color.BLUE);
        meanPaint.setStrokeWidth(5f);
        stdDevPaint.setColor(Color.YELLOW);
        stdDevPaint.setStrokeWidth(5f);

        axisPaint.setColor(Color.BLACK);
        axisPaint.setStrokeWidth(2f);

        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(40f);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight();

        // Find the global max value for scaling
        float globalMax = calculateGlobalMax();

        // Draw axes
        canvas.drawLine(100, 0, 100, height - 100, axisPaint); // Y-axis
        canvas.drawLine(100, height - 100, width, height - 100, axisPaint); // X-axis

        // Draw labels
        canvas.drawText("Time", width / 2f, height - 20, textPaint);
        canvas.drawText("Data", 20, height / 2f, textPaint);

        drawGrid(canvas, width, height);

        // Plot the values with normalization
        drawGraph(canvas, sensorValues, valuePaint, height, globalMax);
        drawGraph(canvas, runningMean, meanPaint, height, globalMax);
        drawGraph(canvas, runningStdDev, stdDevPaint, height, globalMax);

        // Draw legend
        canvas.drawText("Value", 120, 50, valuePaint);
        canvas.drawText("Mean", 120, 100, meanPaint);
        canvas.drawText("Std Dev", 120, 150, stdDevPaint);
    }

    private void drawGrid(Canvas canvas, int width, int height) {
        Paint gridPaint = new Paint();
        gridPaint.setColor(Color.LTGRAY); // Light gray for the grid
        gridPaint.setStrokeWidth(1f);    // Thin grid lines

        // Draw vertical grid lines
        for (int x = 100; x < width; x += GRID_SPACING) {
            canvas.drawLine(x, 0, x, height - 100, gridPaint);
        }

        // Draw horizontal grid lines
        for (int y = 0; y < height - 100; y += GRID_SPACING) {
            canvas.drawLine(100, y, width, y, gridPaint);
        }
    }

    private float calculateMaxYValue() {
        if (sensorValues.isEmpty()) {
            return 50f; // Default max value if there are no sensor values
        }
        return Collections.max(sensorValues);
    }

    private float calculateGlobalMax() {
        float maxSensor = sensorValues.isEmpty() ? 0 : Collections.max(sensorValues);
        float maxMean = runningMean.isEmpty() ? 0 : Collections.max(runningMean);
        float maxStdDev = runningStdDev.isEmpty() ? 0 : Collections.max(runningStdDev);

        // Return the maximum value across all datasets
        return Math.max(maxSensor, Math.max(maxMean, maxStdDev));
    }


    private void drawGraph(Canvas canvas, List<Float> values, Paint paint, int height, float globalMax) {
        if (values.size() < 2) return;

        float xStep = (float) (getWidth() - 100) / maxPoints;

        for (int i = 1; i < values.size(); i++) {
            float startX = 100 + (i - 1) * xStep;
            float startY = height - 100 - (values.get(i - 1) / globalMax) * (height - 200); // Normalize
            float stopX = 100 + i * xStep;
            float stopY = height - 100 - (values.get(i) / globalMax) * (height - 200);     // Normalize

            canvas.drawLine(startX, startY, stopX, stopY, paint);
        }
    }
    public void addSensorValue(float value) {
        if (sensorValues.size() >= maxPoints) {
            sensorValues.remove(0);
        }
        sensorValues.add(value);

        // Calculate running mean and standard deviation
        calculateStats();
        invalidate(); // Redraw the view
    }

    private void calculateStats() {
        int size = sensorValues.size();
        if (size == 0) return;

        float sum = 0f;
        for (float value : sensorValues) {
            sum += value;
        }
        float mean = sum / size;

        float variance = 0f;
        for (float value : sensorValues) {
            variance += Math.pow(value - mean, 2);
        }
        variance /= size;
        float stdDev = (float) Math.sqrt(variance);

        if (runningMean.size() >= maxPoints) {
            runningMean.remove(0);
            runningStdDev.remove(0);
        }
        runningMean.add(mean);
        runningStdDev.add(stdDev);
    }

}
