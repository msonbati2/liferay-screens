/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.mobile.screens.ddl.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.liferay.mobile.screens.context.LiferayServerContext;
import com.liferay.mobile.screens.util.LiferayLogger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

/**
 * @author Jose Manuel Navarro
 */
public class DateField extends Field<Date> {

    public static final Parcelable.ClassLoaderCreator<DateField> CREATOR =
        new Parcelable.ClassLoaderCreator<DateField>() {

            @Override
            public DateField createFromParcel(Parcel source, ClassLoader loader) {
                return new DateField(source, loader);
            }

            public DateField createFromParcel(Parcel in) {
                throw new AssertionError();
            }

            public DateField[] newArray(int size) {
                return new DateField[size];
            }
        };

    public DateField() {
        super();
    }

    public DateField(Map<String, Object> attributes, Locale locale, Locale defaultLocale) {
        super(attributes, locale, defaultLocale);
    }

    protected DateField(Parcel source, ClassLoader loader) {
        super(source, loader);
    }

    @Override
    protected Date convertFromString(String stringValue) {
        if (stringValue == null || stringValue.isEmpty() || stringValue.length() < 6) {
            return null;
        }

        int lastSeparator = stringValue.lastIndexOf('/');

        if (stringValue.contains("-")) {
            return convertDate("yyyy-MM-dd", stringValue);
        } else if (isTimestampFormat(lastSeparator)) {
            return new Date(Long.parseLong(stringValue));
        } else if (isSimpleYearFormat(stringValue, lastSeparator)) {
            return convertDate("MM/dd/yy", stringValue);
        } else {
            return convertDate("MM/dd/yyyy", stringValue);
        }
    }

    @Override
    protected String convertToData(Date value) {
        if (value == null) {
            return null;
        }

        if (LiferayServerContext.isLiferay7()) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", getCurrentLocale());

            return simpleDateFormat.format(value);
        }

        return Long.valueOf(value.getTime()).toString();
    }

    @Override
    protected String convertToFormattedString(Date value) {
        return (value == null) ? "" : DateFormat.getDateInstance(DateFormat.LONG, getCurrentLocale()).format(value);
    }

    private Date convertDate(String pattern, String stringValue) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, getCurrentLocale());
            return simpleDateFormat.parse(stringValue);
        } catch (ParseException e) {
            LiferayLogger.e("Error parsing date " + stringValue);
        }

        return null;
    }

    private boolean isSimpleYearFormat(String stringValue, int lastSeparator) {
        return stringValue.length() - lastSeparator - 1 == 2;
    }

    private boolean isTimestampFormat(int lastSeparator) {
        return lastSeparator == -1;
    }
}