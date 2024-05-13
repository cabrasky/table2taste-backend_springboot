package net.cabrasky.table2taste.backend.modelDto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO implements ModelDTOInterface<Integer> {
    @JsonProperty("id")
    public Integer id;

    @JsonProperty("name")
    public String name;

    @JsonProperty("groups")
    public Set<GroupDTO> groups;
}
