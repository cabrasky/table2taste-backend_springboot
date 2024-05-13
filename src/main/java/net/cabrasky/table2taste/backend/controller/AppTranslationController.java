package net.cabrasky.table2taste.backend.controller;

import net.cabrasky.table2taste.backend.model.AppTranslation;
import net.cabrasky.table2taste.backend.modelDto.AppTranslationDTO;
import net.cabrasky.table2taste.backend.service.AppTranslationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/appTranslations")
public class AppTranslationController extends AbstractModificableController<AppTranslation, AppTranslationDTO, Integer, AppTranslationService>{
	
}
