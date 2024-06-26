package com.example.sharediary.diary.domain;

import com.example.sharediary.diary.dto.DiaryRequestDto;
import com.example.sharediary.member.domain.Member;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Diary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "DIARY_ID")
    private Long diaryId;

    // Member와의 관계 설정
    @ManyToOne
    @JoinColumn(name = "MEMBER_ID", nullable = false)
    private Member member;

    // 제목
    @Column(nullable = false)
    private String title;

    // 내용
    @Column(name = "DIARY_CONTENT", columnDefinition = "TEXT", nullable = false)
    private String content;

    public void update(DiaryRequestDto diaryRequestDto) {
        this.title = diaryRequestDto.getTitle();
        this.content = diaryRequestDto.getContent();
    }

}
