package com.blessing333.stove.modules.post;

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
    private boolean published;

    public PostDTO(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        setPreviewContent(content);
        this.author = post.getAuthor();
        this.createdDate = post.getCreatedDate();
        this.published = isPublished();
    }

    private void setPreviewContent(String content){
        String htmlTagRemovedText = removeHtmlTagString(content);
        this.previewContent = htmlTagRemovedText.length() > 200 ? shorteningContentLength(htmlTagRemovedText,200) : htmlTagRemovedText;
    }

    private String removeHtmlTagString(String content){
        return content.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "");
    }

    private String shorteningContentLength(String content,int limit){
        return content.substring(0,limit)+" ...";
    }
}
