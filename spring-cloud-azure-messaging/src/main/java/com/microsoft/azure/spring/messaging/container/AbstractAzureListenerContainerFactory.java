/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See LICENSE in the project root for
 * license information.
 */

package com.microsoft.azure.spring.messaging.container;

import com.microsoft.azure.spring.integration.core.api.SubscribeByGroupOperation;
import com.microsoft.azure.spring.messaging.endpoint.AbstractAzureListenerEndpoint;
import com.microsoft.azure.spring.messaging.endpoint.AzureListenerEndpoint;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Base {@link ListenerContainerFactory} for Spring's base container implementation.
 *
 * @param <C> the container type
 * @author Warren Zhu
 * @see AbstractAzureListenerEndpoint
 */
@Getter
@AllArgsConstructor
abstract class AbstractAzureListenerContainerFactory<C extends AbstractListenerContainer>
        implements ListenerContainerFactory<C> {

    private final SubscribeByGroupOperation subscribeOperation;

    @Override
    public C createListenerContainer(AzureListenerEndpoint endpoint) {
        C instance = createContainerInstance();
        initializeContainer(instance);
        endpoint.setupListenerContainer(instance);
        return instance;
    }

    /**
     * Create an empty container instance.
     */
    protected abstract C createContainerInstance();

    /**
     * Further initialize the specified container.
     * <p>Subclasses can inherit from this method to apply extra
     * configuration if necessary.
     */
    protected void initializeContainer(C instance) {
    }

}
