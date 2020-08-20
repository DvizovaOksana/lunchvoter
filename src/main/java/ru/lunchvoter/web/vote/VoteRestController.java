package ru.lunchvoter.web.vote;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.lunchvoter.AuthorizedUser;
import ru.lunchvoter.model.Vote;
import ru.lunchvoter.service.VoteService;
import ru.lunchvoter.web.SecurityUtil;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(value = VoteRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteRestController {
    static final String REST_URL = "/rest/votes";

    protected final Logger log = LoggerFactory.getLogger(getClass());

    private final VoteService voteService;

    @Autowired
    public VoteRestController(VoteService voteService) {
        this.voteService = voteService;
    }

    @GetMapping()
    public List<Vote> getOwn(@RequestParam(required = false)
                             @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date, @AuthenticationPrincipal AuthorizedUser authUser) {
        int userId = authUser.getId();
        log.info("get votes for user {} and date {}", userId, date);
        return (date == null)
                ? voteService.getAllByUser(userId)
                : Collections.singletonList(voteService.getByUserAndDate(userId, date));
    }
}
