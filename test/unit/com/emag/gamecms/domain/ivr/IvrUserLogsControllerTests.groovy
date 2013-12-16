package com.emag.gamecms.domain.ivr

import com.emag.gamecms.controllers.ivr.IvrUserLogsController
import grails.test.mixin.*

@TestFor(IvrUserLogsController)
@Mock(IvrUserLogs)
class IvrUserLogsControllerTests {

  def populateValidParams(params) {
    assert params != null
    // TODO: Populate valid properties like...
    //params["name"] = 'someValidName'
  }

  void testIndex() {
    controller.index()
    assert "/ivrUserLogs/list" == response.redirectedUrl
  }

  void testList() {

    def model = controller.list()

    assert model.ivrUserLogsInstanceList.size() == 0
    assert model.ivrUserLogsInstanceTotal == 0
  }

  void testCreate() {
    def model = controller.create()

    assert model.ivrUserLogsInstance != null
  }

  void testSave() {
    controller.save()

    assert model.ivrUserLogsInstance != null
    assert view == '/ivrUserLogs/create'

    response.reset()

    populateValidParams(params)
    controller.save()

    assert response.redirectedUrl == '/ivrUserLogs/show/1'
    assert controller.flash.message != null
    assert IvrUserLogs.count() == 1
  }

  void testShow() {
    controller.show()

    assert flash.message != null
    assert response.redirectedUrl == '/ivrUserLogs/list'

    populateValidParams(params)
    def ivrUserLogs = new IvrUserLogs(params)

    assert ivrUserLogs.save() != null

    params.id = ivrUserLogs.id

    def model = controller.show()

    assert model.ivrUserLogsInstance == ivrUserLogs
  }

  void testEdit() {
    controller.edit()

    assert flash.message != null
    assert response.redirectedUrl == '/ivrUserLogs/list'

    populateValidParams(params)
    def ivrUserLogs = new IvrUserLogs(params)

    assert ivrUserLogs.save() != null

    params.id = ivrUserLogs.id

    def model = controller.edit()

    assert model.ivrUserLogsInstance == ivrUserLogs
  }

  void testUpdate() {
    controller.update()

    assert flash.message != null
    assert response.redirectedUrl == '/ivrUserLogs/list'

    response.reset()

    populateValidParams(params)
    def ivrUserLogs = new IvrUserLogs(params)

    assert ivrUserLogs.save() != null

    // test invalid parameters in update
    params.id = ivrUserLogs.id
    //TODO: add invalid values to params object

    controller.update()

    assert view == "/ivrUserLogs/edit"
    assert model.ivrUserLogsInstance != null

    ivrUserLogs.clearErrors()

    populateValidParams(params)
    controller.update()

    assert response.redirectedUrl == "/ivrUserLogs/show/$ivrUserLogs.id"
    assert flash.message != null

    //test outdated version number
    response.reset()
    ivrUserLogs.clearErrors()

    populateValidParams(params)
    params.id = ivrUserLogs.id
    params.version = -1
    controller.update()

    assert view == "/ivrUserLogs/edit"
    assert model.ivrUserLogsInstance != null
    assert model.ivrUserLogsInstance.errors.getFieldError('version')
    assert flash.message != null
  }

  void testDelete() {
    controller.delete()
    assert flash.message != null
    assert response.redirectedUrl == '/ivrUserLogs/list'

    response.reset()

    populateValidParams(params)
    def ivrUserLogs = new IvrUserLogs(params)

    assert ivrUserLogs.save() != null
    assert IvrUserLogs.count() == 1

    params.id = ivrUserLogs.id

    controller.delete()

    assert IvrUserLogs.count() == 0
    assert IvrUserLogs.get(ivrUserLogs.id) == null
    assert response.redirectedUrl == '/ivrUserLogs/list'
  }
}
