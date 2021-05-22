package com.hohuyhoangg.salesmanager18110284.utils;


import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import com.hohuyhoangg.salesmanager18110284.R;

import java.io.ByteArrayOutputStream;

/**
 * This class provide methods that help you to work with <b><Base64 Encryption/b>
 */
public class Base64Utils {
//   /**
//    * Encode plain text to base64
//    *
//    * @param planText                     plan text to encode
//    * @return                             base64 text
//    * @see org.apache.commons.codec.binary.Base64#encodeBase64(byte[])
//    */
//   public static String encodeFromString(String planText) {
//      byte[] bytesEncoded = Base64.encodeBase64(planText.getBytes());
//      return new String(bytesEncoded);
//   }
//
//   /**
//    * Decode Base64 text to plain text
//    *
//    * @param base64Text                   Base64 text to decode
//    * @return                             plan text
//    * @see org.apache.commons.codec.binary.Base64#decodeBase64(byte[])
//    */
//   public static String decodeToString(String base64Text) {
//      byte[] valueDecoded = Base64.decodeBase64(base64Text);
//      return new String(valueDecoded);
//   }

   public static String bitmapToString (Bitmap bitmap) {
      ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
      bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
      byte[] imageBytes = byteArrayOutputStream.toByteArray();
      return Base64.encodeToString(imageBytes, 1);
   }

   public static Bitmap stringToBitmap (String base64Text) {
      try {
         byte[] decodedString = Base64.decode(base64Text, Base64.DEFAULT);
         return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
      } catch (Exception e) {
         e.printStackTrace();
         return BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.ic_baseline_person_outline_24);
      }
   }
}