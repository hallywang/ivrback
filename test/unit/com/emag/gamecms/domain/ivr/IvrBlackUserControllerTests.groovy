package com.emag.gamecms.domain.ivr

import com.emag.gamecms.controllers.ivr.IvrBlackUserController
import grails.test.mixin.*

@TestFor(IvrBlackUserController)
@Mock(IvrBlackUser)
class IvrBlackUserControllerTests {

  def populateValidParams(params) {
    assert params != null
    // TODO: Populate valid properties like...
    //params["name"] = 'someValidName'
  }

  void testIndex() {
    controller.index()
    assert "/ivrBlackUser/list" == response.redirectedUrl
  }

  void testList() {

    def model = controller.list()

    assert model.ivrBlackUserInstanceList.size() == 0
    assert model.ivrBlackUserInstanceTotal == 0
  }

  void testCreate() {
    def model = controller.create()

    assert model.ivrBlackUserInstance != null
  }

  void testSave() {
    controller.save()

    assert model.ivrBlackUserInstance != null
    assert view == '/ivrBlackUser/create'

    response.reset()

    populateValidParams(params)
    controller.save()

    assert response.redirectedUrl == '/ivrBlackUser/show/1'
    assert controller.flash.message != null
    assert IvrBlackUser.count() == 1
  }

  void testShow() {
    controller.show()

    assert flash.message != null
    assert response.redirectedUrl == '/ivrBlackUser/list'

    populateValidParams(params)
    def ivrBlackUser = new IvrBlackUser(params)

    assert ivrBlackUser.save() != null

    params.id = ivrBlackUser.id

    def model = controller.show()

    assert model.ivrBlackUserInstance == ivrBlackUser
  }

  void testEdit() {
    controller.edit()

    assert flash.message != null
    assert response.redirectedUrl == '/ivrBlackUser/list'

    populateValidParams(params)
    def ivrBlackUser = new IvrBlackUser(params)

    assert ivrBlackUser.save() != null

    params.id = ivrBlackUser.id

    def model = controller.edit()

    assert model.ivrBlackUserInstance == ivrBlackUser
  }

  void testUpdate() {
    controller.update()

    assert flash.message != null
    assert response.redirectedUrl == '/ivrBlackUser/list'

    response.reset()

    populateValidParams(params)
    def ivrBlackUser = new IvrBlackUser(params)

    assert ivrBlackUser.save() != null

    // test invalid parameters in update
    params.id = ivrBlackUser.id
    //TODO: add invalid values to params object

    controller.update()

    assert view == "/ivrBlackUser/edit"
    assert model.ivrBlackUserInstance != null

    ivrBlackUser.clearErrors()

    populateValidParams(params)
    controller.update()

    assert response.redirectedUrl == "/ivrBlackUser/show/$ivrBlackUser.id"
    assert flash.message != null

    //test outdated version number
    response.reset()
    ivrBlackUser.clearErrors()

    populateValidParams(params)
    params.id = ivrBlackUser.id
    params.version = -1
    controller.update()

    assert view == "/ivrBlackUser/edit"
    assert model.ivrBlackUserInstance != null
    assert model.ivrBlackUserInstance.errors.getFieldError('version')
    assert flash.message != null
  }

  void testDelete() {
    controller.delete()
    assert flash.message != null
    assert response.redirectedUrl == '/ivrBlackUser/list'

    response.reset()

    populateValidParams(params)
    def ivrBlackUser = new IvrBlackUser(params)

    assert ivrBlackUser.save() != null
    assert IvrBlackUser.count() == 1

    params.id = ivrBlackUser.id

    controller.delete()

    assert IvrBlackUser.count() == 0
    assert IvrBlackUser.get(ivrBlackUser.id) == null
    assert response.redirectedUrl == '/ivrBlackUser/list'
  }
}
