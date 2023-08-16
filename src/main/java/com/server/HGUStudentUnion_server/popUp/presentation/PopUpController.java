package com.server.HGUStudentUnion_server.popUp.presentation;

import com.server.HGUStudentUnion_server.banner.domain.PopUp;
import com.server.HGUStudentUnion_server.popUp.application.PopUpService;
import com.server.HGUStudentUnion_server.popUp.presentation.request.PopUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PopUpController {
    @Autowired
    private PopUpService popUpService;

    @GetMapping("/popUps")
    public ResponseEntity<List<PopUp>> findAll(){
        return ResponseEntity.ok(popUpService.findAll());
    }
    @GetMapping("/popUps/{id}")
    public ResponseEntity<PopUp> findById(@PathVariable Long id){
        return ResponseEntity.ok(popUpService.findById(id));
    }
    @PostMapping("/popUps")
    public ResponseEntity<PopUp> save(@RequestBody PopUpRequest request){
        PopUp res = popUpService.save(request);
        return ResponseEntity.ok(res);
    }

    @PatchMapping("/popUps/{id}")
    public ResponseEntity<PopUp> update(@PathVariable Long id,@RequestBody PopUpRequest request){
        return ResponseEntity.ok(popUpService.update(id, request));
    }
    @DeleteMapping("/popUps/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long id){
        popUpService.delete(id);
        return ResponseEntity.ok(id);
    }

}
