package com.whiteboardapp.core.pipeline;



import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import com.whiteboardapp.controller.MatConverter;
import com.whiteboardapp.core.pipeline.Binarization;
import com.whiteboardapp.core.pipeline.ChangeDetector;
import com.whiteboardapp.core.pipeline.Segmentator;

import org.opencv.android.Utils;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import com.whiteboardapp.common.AppUtils;

public class ColourExtractor {
    public static Mat Comparison(Mat image, Mat originalImage){
        Core.bitwise_and(originalImage, originalImage, image);
         return image;

    }

}
