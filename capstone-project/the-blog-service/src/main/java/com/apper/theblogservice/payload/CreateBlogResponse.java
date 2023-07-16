package com.apper.theblogservice.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class CreateBlogResponse {
    private String title;
    private String body;

    @JsonProperty("blogger_id")
    private String bloggerId;

    @JsonProperty("id")
    private String id;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("last_updated")
    private LocalDateTime lastUpdated;
}
