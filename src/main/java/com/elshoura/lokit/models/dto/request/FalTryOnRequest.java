package com.elshoura.lokit.models.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record FalTryOnRequest(

        @JsonProperty("model_image")
        String modelImage,

        @JsonProperty("garment_image")
        String garmentImage,

        String category,

        String mode,

        @JsonProperty("garment_photo_type")
        String garmentPhotoType,

        @JsonProperty("moderation_level")
        String moderationLevel,

        @JsonProperty("num_samples")
        Integer numSamples,

        @JsonProperty("segmentation_free")
        Boolean segmentationFree,

        @JsonProperty("output_format")
        String outputFormat

        ) {
}
