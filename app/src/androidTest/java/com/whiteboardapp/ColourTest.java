package com.whiteboardapp;

import static android.content.ContentValues.TAG;

import android.graphics.Bitmap;
import android.util.Log;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.whiteboardapp.controller.MatConverter;
import com.whiteboardapp.core.pipeline.Binarization;
import com.whiteboardapp.core.pipeline.ColourExtractor;
import com.whiteboardapp.core.pipeline.Segmentator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

@RunWith(AndroidJUnit4)
public class ColourTest {

    @Test
    public void testColour(){
        Mat imgBgr =
        Mat matPerspectiveRgb = new Mat();
        Imgproc.cvtColor(imgBgr, matPerspectiveRgb, Imgproc.COLOR_BGR2RGB);
        Segmentator segmentator = new Segmentator(appContext);
        Bitmap bitmapRgb = MatConverter.matToBitmap(matPerspectiveRgb);
        Mat imgSegMap = segmentator.segmentate(bitmapRgb);
        Log.d(TAG, "capture: Segmentation done.");

        // Binarize a gray scale version of the image.
        Mat imgWarpGray = new Mat();
        Imgproc.cvtColor(imgBgr, imgWarpGray, Imgproc.COLOR_BGR2GRAY);
        Mat imgBinarized = Binarization.binarize(imgWarpGray);

        // Remove segments before change detection.
        Mat currentModelCopy = removeSegmentArea(imgBinarized, currentModel, imgSegMap, imgBgr);

        // Change detection
        Mat imgPersistentChanges = changeDetector.detectChanges(imgBinarized, currentModelCopy);

        // Compare Change-detected Binarized with original
        Mat colouredChanges = ColourExtractor.Comparison(imgPersistentChanges, imgBgr);


    }

}
