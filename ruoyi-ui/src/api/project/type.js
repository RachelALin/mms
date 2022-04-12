import request from '@/utils/request'

// 查询项目类型列表
export function listType(query) {
  return request({
    url: '/project/type/list',
    method: 'get',
    params: query
  })
}

// 查询项目类型详细
export function getType(id) {
  return request({
    url: '/project/type/' + id,
    method: 'get'
  })
}

// 新增项目类型
export function addType(data) {
  return request({
    url: '/project/type',
    method: 'post',
    data: data
  })
}

// 修改项目类型
export function updateType(data) {
  return request({
    url: '/project/type',
    method: 'put',
    data: data
  })
}

// 删除项目类型
export function delType(id) {
  return request({
    url: '/project/type/' + id,
    method: 'delete'
  })
}
