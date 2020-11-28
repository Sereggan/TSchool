package org.tsystems.tschool.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Value data transfer object
 * Class to access and control article
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ValueDto {
    private Long valueId;

    private Long articleId;
}
