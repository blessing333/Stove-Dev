package com.blessing333.stove.modules.post;

import com.blessing333.stove.infra.config.BlogConfig;
import com.blessing333.stove.modules.category.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class PostDTO {
    private Long id;
    private String title;
    private String content;
    private String previewContent;
    private String author;
    private LocalDateTime createdDate;
    private Category category;
    private boolean published;
    private String thumbnail;

    public PostDTO(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        setPreviewContent(content);
        this.author = post.getAuthor();
        this.createdDate = post.getCreatedDate();
        this.published = isPublished();
        this.category = post.getCategory();
        this.thumbnail = post.getThumbnail();
    }

    private void setPreviewContent(String content){
        String htmlTagRemovedText = removeHtmlTagString(content);
        this.previewContent = htmlTagRemovedText.length() > BlogConfig.POST_PREVIEW_MAX_LENGTH ? shorteningContentLength(htmlTagRemovedText, BlogConfig.POST_PREVIEW_MAX_LENGTH) : htmlTagRemovedText;
    }

    private String removeHtmlTagString(String content){
        String tagRemovedContent = content.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "");
        return tagRemovedContent.replaceAll("&nbsp;","");
    }

    private String shorteningContentLength(String content,int limit){
        return content.substring(0,limit)+" ...";
    }
}
