package work.takatan.worksauth.application.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import work.takatan.worksauth.application.request.AuthorizationRequest;
import work.takatan.worksauth.domain.object.auth.Authorization;
import work.takatan.worksauth.domain.service.AuthorizationService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/auth/validation")
public class AuthController {

    private final AuthorizationService authorizationService;


    @RequestMapping(method = RequestMethod.POST, path = "/validation")
    public Authorization validation(@RequestBody AuthorizationRequest authorizationRequest) {
        return authorizationService.handle(authorizationRequest);
    }
}
