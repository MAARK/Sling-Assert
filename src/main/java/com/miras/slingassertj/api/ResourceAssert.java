package com.miras.slingassertj.api;

import org.apache.sling.api.resource.Resource;

public class ResourceAssert extends AbstractResourceAssert<ResourceAssert> {

    ResourceAssert(Resource resource) {
        super(resource, ResourceAssert.class);
    }
}
