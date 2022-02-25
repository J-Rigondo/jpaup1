package com.jpa.jpaup1.api;

import com.jpa.jpaup1.domain.Member;
import com.jpa.jpaup1.dto.ApiResponse;
import com.jpa.jpaup1.dto.member.*;
import com.jpa.jpaup1.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Api(tags = "멤버 API")
@RestController //controller + responseBody
@RequiredArgsConstructor
@RequestMapping("member/api")
public class MemberApiController {

    private final MemberService memberService;

    @ApiOperation(value = "멤버 저장")
    @PostMapping("/member")
    public ResponseEntity<ApiResponse<Long>> saveMemberV1(@RequestBody @Valid CreateMemberRequest request) {

        Member member = new Member();
        member.setName(request.getName());
        Long id = memberService.join(member);

        return ResponseEntity.ok(ApiResponse.success(id));
    }

    @ApiOperation(value = "멤버 수정")
    @PutMapping("/member/{id}")
    public ResponseEntity<ApiResponse<Long>> saveMemberV1(@RequestBody UpdateMemberRequest request,
                                                          @PathVariable("id") Long id) {

        memberService.update(id, request);

        return ResponseEntity.ok(ApiResponse.success(id));

    }

    @GetMapping("/member/list")
    public ResponseEntity<ApiResponse<List<Member>>> getMemberList() {

        List<Member> findMembers = memberService.findMembers();
        List<MemberListResponse.MemberDto> members = findMembers.stream()
                .map(member -> MemberListResponse.MemberDto.builder().name(member.getName()).build()).collect(Collectors.toList());


        return ResponseEntity.ok(ApiResponse.success(members));

    }

    @GetMapping("/member/{id}")
    public ResponseEntity<MemberResponse> getMember(@PathVariable("id") Long id) {
        MemberResponse one = memberService.findOne(id);
        return ResponseEntity.ok(one);
    }

}
