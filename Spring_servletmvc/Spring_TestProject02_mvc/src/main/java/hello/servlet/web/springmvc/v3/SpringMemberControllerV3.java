package hello.servlet.web.springmvc.v3;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/springmvc/v3/members")
public class SpringMemberControllerV3 {

    MemberRepository memberRepository = MemberRepository.getInstance();

    @RequestMapping(value = "/new-form" , method = RequestMethod.GET)
    public String save() {
        return "new-form";
    }

    @RequestMapping(value = "/save" , method = RequestMethod.POST)
    public String result(
            @RequestParam("username") String username,
            @RequestParam("age") int age,
            Model model)
    {
        Member member = new Member(username, age);
        memberRepository.save(member);
        model.addAttribute("member", member);
        return "save";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model){
        List<Member> memberList = memberRepository.findAll();
        model.addAttribute("members", memberList);
        return "members";
    }
}
