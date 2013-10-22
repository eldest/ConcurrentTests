/*
 * Copyright (C) 2008-2009 Eremenko EremenkoAA@ics-ware.com
 * 
 * Information Corporative Systems, ICS LLC
 * TelaDictum LLC
 *
 * Time
 * 
 * Created: 03.05.2010
 * History: 
 *    
 *
 * $Id$
 * 
 */
package com.eldest.old.annotations.time;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author EremenkoAA
 *
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.SOURCE)
public @interface Time {
    public enum TimeInterval { MILLISECOND, NANOSECOND };
    
    TimeInterval interval() default TimeInterval.MILLISECOND;
    String format() default "Elapsed %s";
}