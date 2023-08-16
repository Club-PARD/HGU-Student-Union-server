package com.server.HGUStudentUnion_server.suggest.presentation.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.server.HGUStudentUnion_server.appUser.domain.AppUser;
import com.server.HGUStudentUnion_server.suggest.domain.Suggest;
import com.server.HGUStudentUnion_server.suggest.domain.SuggestAppUser;
import com.server.HGUStudentUnion_server.suggest.domain.repository.SuggestAppUserRepo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SuggestDetailResponse {

    private Long id;
    private String writer; // writer
    private int recommendCnt;
    private Boolean recommended;
    private Boolean hide;
    private String title;
    private String content;
    private int status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime updatedAt;
    private int viewCnt;

    private AppUser ansUser;
    private String answer;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime ansTime;

    public static SuggestDetailResponse of(Suggest suggest, Boolean recommended, int recommendCnt){
        return new SuggestDetailResponse(suggest.getId(),
                suggest.getAppUser().getName(), recommendCnt, recommended,
                suggest.getHide(), suggest.getTitle(), suggest.getContent(),
                suggest.getStatus(), suggest.getCreatedAt(), suggest.getUpdatedAt(),
                suggest.getViewCnt(), suggest.getAnsUser(), suggest.getAnswer(),
                suggest.getAnsTime());
    }

}
