// SPDX-License-Identifier: Apache-2.0
// Copyright Contributors to the OpenTimelineIO Project.

package io.opentimeline.opentimelineio;

import io.opentimeline.OTIONative;
import io.opentimeline.opentime.RationalTime;
import io.opentimeline.opentime.TimeRange;
import io.opentimeline.util.Pair;

/**
 * An ImageSequenceReference refers to a numbered series of single-frame image files.
 * Each file can be referred to by a URL generated by the ImageSequenceReference.
 * <p>
 * Image sequences can have URLs with discontinuous frame numbers,
 * for instance if you've only rendered every other frame in a sequence,
 * your frame numbers may be 1, 3, 5, etc.
 * This is configured using the ``frame_step`` attribute.
 * In this case, the 0th image in the sequence is frame 1 and the 1st image
 * in the sequence is frame 3. Because of this there are two numbering concepts
 * in the image sequence, the image number and the frame number.
 * <p>
 * Frame numbers are the integer numbers used in the frame file name.
 * Image numbers are the 0-index based numbers of the frames available in the reference.
 * Frame numbers can be discontinuous, image numbers will always be zero to the total count of frames minus 1.
 * <p>
 * Negative <em>startFrame</em> is also handled.
 * eg:
 * - "file:///show/sequence/shot/sample_image_sequence.-0001.exr"
 * - "file:///show/sequence/shot/sample_image_sequence.0000.exr"
 * - "file:///show/sequence/shot/sample_image_sequence.0001.exr"
 */
public class ImageSequenceReference extends MediaReference {

    /**
     * enum ImageSequenceReference.MissingFramePolicy directive for
     * how frames in sequence not found on disk should be handled.
     */
    public enum MissingFramePolicy {
        /**
         * Application should abort and raise an error.
         */
        error,
        /**
         * Application should hold the last available frame before the missing frame.
         */
        hold,
        /**
         * Application should use a black frame in place of the missing frame.
         */
        black,
    }

    protected ImageSequenceReference() {
    }

    ImageSequenceReference(OTIONative otioNative) {
        this.nativeManager = otioNative;
    }

    /**
     * @param targetURLBase      Everything leading up to the file name in the target_url.
     * @param namePrefix         Everything in the file name leading up to the frame number.
     * @param nameSuffix         Everything after the frame number in the file name.
     * @param startFrame         The first frame number used in file names.
     * @param frameStep          Step between frame numbers in file names.
     * @param rate               Frame rate if every frame in the sequence were played back.
     * @param frameZeroPadding   Number of digits to pad zeros out to in frame numbers.
     * @param missingFramePolicy enum ImageSequenceReference.MissingFramePolicy directive for how frames in sequence not found on disk should be handled.
     * @param availableRange     available range
     * @param metadata           metadata dictionary
     */
    public ImageSequenceReference(
            String targetURLBase,
            String namePrefix,
            String nameSuffix,
            int startFrame,
            int frameStep,
            double rate,
            int frameZeroPadding,
            MissingFramePolicy missingFramePolicy,
            TimeRange availableRange,
            AnyDictionary metadata) {
        this.initObject(
                targetURLBase,
                namePrefix,
                nameSuffix,
                startFrame,
                frameStep,
                rate,
                frameZeroPadding,
                missingFramePolicy,
                availableRange,
                metadata);
    }

    public ImageSequenceReference(ImageSequenceReference.ImageSequenceReferenceBuilder imageSequenceReferenceBuilder) {
        this.initObject(
                imageSequenceReferenceBuilder.targetURLBase,
                imageSequenceReferenceBuilder.namePrefix,
                imageSequenceReferenceBuilder.nameSuffix,
                imageSequenceReferenceBuilder.startFrame,
                imageSequenceReferenceBuilder.frameStep,
                imageSequenceReferenceBuilder.rate,
                imageSequenceReferenceBuilder.frameZeroPadding,
                imageSequenceReferenceBuilder.missingFramePolicy,
                imageSequenceReferenceBuilder.availableRange,
                imageSequenceReferenceBuilder.metadata);
    }

    private void initObject(String targetURLBase,
                            String namePrefix,
                            String nameSuffix,
                            int startFrame,
                            int frameStep,
                            double rate,
                            int frameZeroPadding,
                            MissingFramePolicy missingFramePolicy,
                            TimeRange availableRange,
                            AnyDictionary metadata) {
        this.initialize(
                targetURLBase,
                namePrefix,
                nameSuffix,
                startFrame,
                frameStep,
                rate,
                frameZeroPadding,
                missingFramePolicy.ordinal(),
                availableRange,
                metadata);
        this.nativeManager.className = this.getClass().getCanonicalName();
    }

    private native void initialize(String targetURLBase,
                                   String namePrefix,
                                   String nameSuffix,
                                   int startFrame,
                                   int frameStep,
                                   double rate,
                                   int frameZeroPadding,
                                   int missingFramePolicy,
                                   TimeRange availableRange,
                                   AnyDictionary metadata);

    public static class ImageSequenceReferenceBuilder {
        String targetURLBase = "";
        String namePrefix = "";
        String nameSuffix = "";
        int startFrame = 1;
        int frameStep = 1;
        double rate = 1;
        int frameZeroPadding = 0;
        MissingFramePolicy missingFramePolicy = MissingFramePolicy.error;
        TimeRange availableRange = null;
        AnyDictionary metadata = new AnyDictionary();

        public ImageSequenceReferenceBuilder() {
        }

        public ImageSequenceReference.ImageSequenceReferenceBuilder setTargetURLBase(String targetURLBase) {
            this.targetURLBase = targetURLBase;
            return this;
        }

        public ImageSequenceReference.ImageSequenceReferenceBuilder setNamePrefix(String namePrefix) {
            this.namePrefix = namePrefix;
            return this;
        }

        public ImageSequenceReference.ImageSequenceReferenceBuilder setNameSuffix(String nameSuffix) {
            this.nameSuffix = nameSuffix;
            return this;
        }

        public ImageSequenceReference.ImageSequenceReferenceBuilder setStartFrame(int startFrame) {
            this.startFrame = startFrame;
            return this;
        }

        public ImageSequenceReference.ImageSequenceReferenceBuilder setFrameStep(int frameStep) {
            this.frameStep = frameStep;
            return this;
        }

        public ImageSequenceReference.ImageSequenceReferenceBuilder setRate(double rate) {
            this.rate = rate;
            return this;
        }

        public ImageSequenceReference.ImageSequenceReferenceBuilder setFrameZeroPadding(int frameZeroPadding) {
            this.frameZeroPadding = frameZeroPadding;
            return this;
        }

        public ImageSequenceReference.ImageSequenceReferenceBuilder setMissingFramePolicy(MissingFramePolicy missingFramePolicy) {
            this.missingFramePolicy = missingFramePolicy;
            return this;
        }

        public ImageSequenceReference.ImageSequenceReferenceBuilder setAvailableRange(TimeRange availableRange) {
            this.availableRange = availableRange;
            return this;
        }


        public ImageSequenceReference.ImageSequenceReferenceBuilder setMetadata(AnyDictionary metadata) {
            this.metadata = metadata;
            return this;
        }

        public ImageSequenceReference build() {
            return new ImageSequenceReference(this);
        }
    }

    public native String getTargetURLBase();

    public native void setTargetURLBase(String targetURLBase);

    public native String getNamePrefix();

    public native void setNamePrefix(String namePrefix);

    public native String getNameSuffix();

    public native void setNameSuffix(String nameSuffix);

    public native int getStartFrame();

    public native void setStartFrame(int startFrame);

    public native int getFrameStep();

    public native void setFrameStep(int frameStep);

    public native double getRate();

    public native void setRate(double rate);

    public native int getFrameZeroPadding();

    public native void setFrameZeroPadding(int frameZeroPadding);

    public MissingFramePolicy getMissingFramePolicy() {
        return MissingFramePolicy.values()[getMissingFramePolicyNative()];
    }

    private native int getMissingFramePolicyNative();

    public void setMissingFramePolicy(MissingFramePolicy missingFramePolicy) {
        this.setMissingFramePolicyNative(missingFramePolicy.ordinal());
    }

    private native void setMissingFramePolicyNative(int missingFramePolicy);

    public native int getEndFrame();

    public native int getNumberOfImagesInSequence();

    public native int getFrameForTime(RationalTime rationalTime, ErrorStatus errorStatus);

    public native String getTargetURLForImageNumber(int imageNumber, ErrorStatus errorStatus);

    public native RationalTime presentationTimeForImageNumber(int imageNumber, ErrorStatus errorStatus);

    /**
     * Generates a target url for a frame where `symbol` is used in place
     * of the frame number. This is often used to generate wildcard target urls.
     *
     * @param symbol symbol to be used instead of frame number
     * @return target URL with symbol on place of frame number
     */
    public String getAbstractTargetURL(String symbol) {
        String base = "";
        if (!this.getTargetURLBase().endsWith("/"))
            base = this.getTargetURLBase() + "/";
        else
            base = this.getTargetURLBase();
        return base + this.getNamePrefix() + symbol + this.getNameSuffix();
    }

    /**
     * Returns a Pair&lt;Integer, Integer&gt; containing the first and last frame numbers for
     * the given time range in the reference.
     *
     * @param timeRange   range fore which first and last frame numbers are to be found
     * @param errorStatus errorStatus to report error while fetching frame numbers
     * @return pair of frame numbers
     */
    public Pair<Integer, Integer> getFrameRangeForTimeRange(TimeRange timeRange, ErrorStatus errorStatus) {
        return new Pair<>(this.getFrameForTime(timeRange.getStartTime(), errorStatus),
                this.getFrameForTime(timeRange.endTimeInclusive(), errorStatus));
    }

    @Override
    public String toString() {
        return this.getClass().getCanonicalName() +
                "(" +
                "targetURLBase=" + this.getTargetURLBase() +
                ", namePrefix=" + this.getNamePrefix() +
                ", nameSuffix=" + this.getNameSuffix() +
                ", startFrame=" + this.getStartFrame() +
                ", frameStep=" + this.getFrameStep() +
                ", rate=" + this.getRate() +
                ", frameZeroPadding=" + this.getFrameZeroPadding() +
                ", missingFramePolicy=" + this.getMissingFramePolicy() +
                ", availableRange=" + this.getAvailableRange().toString() +
                ", metadata=" + this.getMetadata().toString() +
                ")";
    }
}