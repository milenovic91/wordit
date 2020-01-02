package com.geniye.wordit.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collections;
import java.util.HashMap;

@RestController
@RequestMapping("/tags")
public class TagsController {

  @RequestMapping(method = RequestMethod.GET)
  public ResponseEntity getAllTags() {
    return ResponseEntity.ok(new HashMap<String, Object>(){{
      put("tags", Collections.emptyList());
    }});
  }
}
