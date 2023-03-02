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
import org.opencv.android.OpenCVLoader;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.whiteboardapp.common.AppUtils;
import com.whiteboardapp.controller.MatConverter;
import com.whiteboardapp.core.CaptureService;
import com.whiteboardapp.core.pipeline.Binarization;
import com.whiteboardapp.core.pipeline.ChangeDetector;
import com.whiteboardapp.core.pipeline.CornerDetector;
import com.whiteboardapp.core.pipeline.PerspectiveTransformer;
import com.whiteboardapp.core.pipeline.Segmentator;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.imgproc.Imgproc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

@RunWith(AndroidJUnit4.class)
public class ColourTest {
    //private final Context appContext;

    // Initialize openCV library
    static {
        if (OpenCVLoader.initDebug()) {
            Log.d("MainActivity: ", "Opencv is loaded!");
        } else {
            Log.d("MainActivity: ", "Open failed to load.");
        }
    }
    @Test
    public void testColour(){

        String path = "../../../../main/assets";


        Mat imgBgr = Imgcodecs.imread(path + "/simpleColour.jpg");
        Mat matPerspectiveRgb = new Mat();
        Imgproc.cvtColor(imgBgr, matPerspectiveRgb, Imgproc.COLOR_BGR2RGB);
        //Segmentator segmentator = new Segmentator(appContext);
        Bitmap bitmapRgb = MatConverter.matToBitmap(matPerspectiveRgb);
        //Mat imgSegMap = segmentator.segmentate(bitmapRgb);
        Log.d(TAG, "capture: Segmentation done.");

        // Binarize a gray scale version of the image.
        Mat imgWarpGray = new Mat();
        Imgproc.cvtColor(imgBgr, imgWarpGray, Imgproc.COLOR_BGR2GRAY);
        Mat imgBinarized = Binarization.binarize(imgWarpGray);

        // Remove segments before change detection.
        //Mat currentModelCopy = removeSegmentArea(imgBinarized, currentModel, imgSegMap, imgBgr);

        // Change detection
        //Mat imgPersistentChanges = changeDetector.detectChanges(imgBinarized, currentModelCopy);

        // Compare Change-detected Binarized with original
        Mat ColouredChanges = ColourExtractor.Comparison(imgBinarized, imgBgr);

        Imgcodecs.imwrite(path+"/ColourTest.jpg", ColouredChanges);

    }

}
