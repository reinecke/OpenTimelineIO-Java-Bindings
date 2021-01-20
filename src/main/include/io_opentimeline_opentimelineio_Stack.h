/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class io_opentimeline_opentimelineio_Stack */

#ifndef _Included_io_opentimeline_opentimelineio_Stack
#define _Included_io_opentimeline_opentimelineio_Stack
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     io_opentimeline_opentimelineio_Stack
 * Method:    initialize
 * Signature: (Ljava/lang/String;Lio/opentimeline/opentime/TimeRange;Lio/opentimeline/opentimelineio/AnyDictionary;[Lio/opentimeline/opentimelineio/Effect;[Lio/opentimeline/opentimelineio/Marker;)V
 */
JNIEXPORT void JNICALL Java_io_opentimeline_opentimelineio_Stack_initialize
  (JNIEnv *, jobject, jstring, jobject, jobject, jobjectArray, jobjectArray);

/*
 * Class:     io_opentimeline_opentimelineio_Stack
 * Method:    rangeOfChildAtIndex
 * Signature: (ILio/opentimeline/opentimelineio/ErrorStatus;)Lio/opentimeline/opentime/TimeRange;
 */
JNIEXPORT jobject JNICALL Java_io_opentimeline_opentimelineio_Stack_rangeOfChildAtIndex
  (JNIEnv *, jobject, jint, jobject);

/*
 * Class:     io_opentimeline_opentimelineio_Stack
 * Method:    trimmedRangeOfChildAtIndex
 * Signature: (ILio/opentimeline/opentimelineio/ErrorStatus;)Lio/opentimeline/opentime/TimeRange;
 */
JNIEXPORT jobject JNICALL Java_io_opentimeline_opentimelineio_Stack_trimmedRangeOfChildAtIndex
  (JNIEnv *, jobject, jint, jobject);

/*
 * Class:     io_opentimeline_opentimelineio_Stack
 * Method:    getAvailableRange
 * Signature: (Lio/opentimeline/opentimelineio/ErrorStatus;)Lio/opentimeline/opentime/TimeRange;
 */
JNIEXPORT jobject JNICALL Java_io_opentimeline_opentimelineio_Stack_getAvailableRange
  (JNIEnv *, jobject, jobject);

/*
 * Class:     io_opentimeline_opentimelineio_Stack
 * Method:    getRangeOfAllChildren
 * Signature: (Lio/opentimeline/opentimelineio/ErrorStatus;)Ljava/util/HashMap;
 */
JNIEXPORT jobject JNICALL Java_io_opentimeline_opentimelineio_Stack_getRangeOfAllChildren
  (JNIEnv *, jobject, jobject);

#ifdef __cplusplus
}
#endif
#endif