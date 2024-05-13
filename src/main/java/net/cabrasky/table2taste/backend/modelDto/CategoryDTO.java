package net.cabrasky.table2taste.backend.modelDto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Optional;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryDTO implements ModelDTOInterface<Integer> {
	@JsonProperty("id")
	public Integer id;
	
    @JsonProperty("mediaUrl")
    public Optional<String> mediaUrl;

    @JsonProperty("parentCategory")
    public Optional<CategoryDTO> parentCategory;

    @JsonProperty("translations")
    public Set<TranslationDTO> translations;
}
