package net.cabrasky.table2taste.backend.modelDto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Optional;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AppTranslationDTO implements ModelDTOInterface<Integer> {
    @JsonProperty("id")
    public Optional<String> id;

    @JsonProperty("translation")
    public Optional<TranslationDTO> translation;

}
