package net.cabrasky.table2taste.backend.service;

import org.springframework.stereotype.Service;

import net.cabrasky.table2taste.backend.model.AppTranslation;
import net.cabrasky.table2taste.backend.modelDto.AppTranslationDTO;
import net.cabrasky.table2taste.backend.repository.AppTranslationRepository;

@Service
public class AppTranslationService
		extends AbstractModificableService<AppTranslation, AppTranslationDTO, Integer, AppTranslationRepository> {

	@Override
	protected Class<AppTranslation> getModelClass() {
		return AppTranslation.class;
	}
}
