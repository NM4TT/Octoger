package com.nmatute.octoger.productmanagement.web.security.config;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

import org.springframework.security.access.prepost.PreAuthorize;

/**
 * Tag personalizada para marcar endpoints solo disponibles para usuarios ADMIN.
 * 
 * @author NM4TT
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("hasAuthority('ADMIN')")
public @interface AdminEndpoint {

}

