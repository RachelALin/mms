import request from '@/utils/request'

// 查询项目立项列表
export function listProject(query) {
  return request({
    url: '/project/project/list',
    method: 'get',
    params: query
  })
}

// 查询项目立项详细
export function getProject(proId) {
  return request({
    url: '/project/project/' + proId,
    method: 'get'
  })
}

// 新增项目立项
export function addProject(data) {
  return request({
    url: '/project/project',
    method: 'post',
    data: data
  })
}

// 修改项目立项
export function updateProject(data) {
  return request({
    url: '/project/project',
    method: 'put',
    data: data
  })
}

// 删除项目立项
export function delProject(proId) {
  return request({
    url: '/project/project/' + proId,
    method: 'delete'
  })
}

// 查用户列表

export function listUsers(query) {
  return request({
    url: '/project/project/userslist',
    method: 'get',
    params: query
  })
}