package org.tsystems.tschool.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Value data transfer object
 * Class to access and control article
 */
@Setter
@Getter
public class ValueDto {
    private Long valueId;

    private Long articleId;
}
