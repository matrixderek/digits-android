/*
 * Copyright (C) 2015 Twitter, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.digits.sdk.android;

import android.content.ComponentName;
import android.content.Intent;
import android.test.mock.MockContext;

import org.mockito.ArgumentCaptor;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ContactsControllerTests extends DigitsAndroidTestCase {
    private ContactsController controller;
    private MockContext context;
    private ArgumentCaptor<Intent> intentCaptor;

    @Override
    public void setUp() throws Exception {
        super.setUp();

        controller = new ContactsControllerImpl();
        context = mock(MockContext.class);
        intentCaptor = ArgumentCaptor.forClass(Intent.class);

        when(context.getPackageName()).thenReturn(ContactsUploadService.class.getPackage()
                .toString());
    }

    public void testStartUploadService() {
        controller.startUploadService(context);

        verify(context).startService(intentCaptor.capture());
        final Intent intent = intentCaptor.getValue();

        final ComponentName component = new ComponentName(context, ContactsUploadService.class
                .getName());
        assertEquals(component, intent.getComponent());
    }
}
