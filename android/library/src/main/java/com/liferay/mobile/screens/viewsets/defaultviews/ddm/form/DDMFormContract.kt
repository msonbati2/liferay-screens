/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 * <p>
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 * <p>
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.mobile.screens.viewsets.defaultviews.ddm.form

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.liferay.mobile.screens.ddl.form.view.DDLFieldViewModel
import com.liferay.mobile.screens.ddl.model.DocumentField
import com.liferay.mobile.screens.ddl.model.DocumentRemoteFile
import com.liferay.mobile.screens.ddl.model.Field
import com.liferay.mobile.screens.ddm.form.DDMFormListener
import com.liferay.mobile.screens.ddm.form.model.*
import com.liferay.mobile.screens.viewsets.defaultviews.ddl.form.fields.DDLDocumentFieldView
import com.liferay.mobile.screens.viewsets.defaultviews.ddm.events.FormEvents
import rx.Observable
import java.io.InputStream

/**
 * @author Victor Oliveira
 */
interface DDMFormViewContract {
	interface DDMFormView {

		val config: DDMFormViewConfig

		var formInstance: FormInstance?

		var ddmFormListener: DDMFormListener?

		fun isSubmitEnabled(isEnabled: Boolean)

		fun hasConnectivity(): Boolean

		fun hideModalLoading()

		fun inflateFieldView(inflater: LayoutInflater, parentView: ViewGroup, field: Field<*>): View

		fun refreshVisibleFields()

		fun setDDMFormListener(listener: DDMFormListener)

		fun scrollToTop()

		fun showErrorMessage(exception: Throwable?)

		fun showModalEvaluateContextLoading()

		fun showModalSyncFormLoading()

		fun showOfflineWarningMessage()

		fun showSuccessMessage()

		fun showSuccessPage(successPage: SuccessPage)

		fun startUpload(documentFieldView: DDLDocumentFieldView)

		fun subscribeToValueChanged(observable: Observable<Field<*>>)

		fun updateFieldView(fieldContext: FieldContext, field: Field<*>)

		fun updatePageEnabled(formContext: FormContext)
	}

	interface DDMFormViewPresenter {

		fun addToDirtyFields(field: Field<*>)

		fun checkIsDirty(field: Field<*>, fieldContext: FieldContext, fieldViewModel: DDLFieldViewModel<*>?)

		fun evaluateContext(formInstance: FormInstance, fields: MutableList<Field<*>>, onComplete: (() -> Unit)? = null)

		fun fieldModelsChanged(field: Field<*>)

		fun getFormInstanceState(): FormInstanceState

		fun loadInitialContext(formInstance: FormInstance)

		fun onDestroy()

		fun restore(
			formInstanceRecord: FormInstanceRecord?, fields: MutableList<Field<*>>,
			formInstanceState: FormInstanceState)

		fun submit(formInstance: FormInstance, isDraft: Boolean = false)

		fun syncForm(formInstance: FormInstance, field: Field<*>? = null, onComplete: (() -> Unit)? = null)

		fun uploadFile(
			formInstance: FormInstance, field: DocumentField, inputStream: InputStream,
			onSuccess: (DocumentRemoteFile) -> Unit,
			onError: (Throwable) -> Unit)
	}
}

