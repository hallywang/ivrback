package com.emag.gamecms.domain.ivr

import com.emag.gamecms.controllers.ivr.IvrServiceInfoController
import grails.test.mixin.*

@TestFor(IvrServiceInfoController)
@Mock(IvrServiceInfo)
class IvrServiceInfoControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/ivrServiceInfo/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.ivrServiceInfoInstanceList.size() == 0
        assert model.ivrServiceInfoInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.ivrServiceInfoInstance != null
    }

    void testSave() {
        controller.save()

        assert model.ivrServiceInfoInstance != null
        assert view == '/ivrServiceInfo/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/ivrServiceInfo/show/1'
        assert controller.flash.message != null
        assert IvrServiceInfo.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/ivrServiceInfo/list'

        populateValidParams(params)
        def ivrServiceInfo = new IvrServiceInfo(params)

        assert ivrServiceInfo.save() != null

        params.id = ivrServiceInfo.id

        def model = controller.show()

        assert model.ivrServiceInfoInstance == ivrServiceInfo
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/ivrServiceInfo/list'

        populateValidParams(params)
        def ivrServiceInfo = new IvrServiceInfo(params)

        assert ivrServiceInfo.save() != null

        params.id = ivrServiceInfo.id

        def model = controller.edit()

        assert model.ivrServiceInfoInstance == ivrServiceInfo
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/ivrServiceInfo/list'

        response.reset()

        populateValidParams(params)
        def ivrServiceInfo = new IvrServiceInfo(params)

        assert ivrServiceInfo.save() != null

        // test invalid parameters in update
        params.id = ivrServiceInfo.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/ivrServiceInfo/edit"
        assert model.ivrServiceInfoInstance != null

        ivrServiceInfo.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/ivrServiceInfo/show/$ivrServiceInfo.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        ivrServiceInfo.clearErrors()

        populateValidParams(params)
        params.id = ivrServiceInfo.id
        params.version = -1
        controller.update()

        assert view == "/ivrServiceInfo/edit"
        assert model.ivrServiceInfoInstance != null
        assert model.ivrServiceInfoInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/ivrServiceInfo/list'

        response.reset()

        populateValidParams(params)
        def ivrServiceInfo = new IvrServiceInfo(params)

        assert ivrServiceInfo.save() != null
        assert IvrServiceInfo.count() == 1

        params.id = ivrServiceInfo.id

        controller.delete()

        assert IvrServiceInfo.count() == 0
        assert IvrServiceInfo.get(ivrServiceInfo.id) == null
        assert response.redirectedUrl == '/ivrServiceInfo/list'
    }
}
