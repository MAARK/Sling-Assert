package com.miras.slingassertj.api;

import org.apache.sling.api.resource.Resource;

public class SlingAssertions {

    public static AbstractResourceAssert<?> assertThat(Resource actual) {
        return new ResourceAssert(actual);
    }

}
