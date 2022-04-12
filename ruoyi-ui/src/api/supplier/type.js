import request from '@/utils/request'

// 查询供应商类型管理列表
export function listType(query) {
  return request({
    url: '/supplier/type/list',
    method: 'get',
    params: query
  })
}

// 查询供应商类型管理详细
export function getType(id) {
  return request({
    url: '/supplier/type/' + id,
    method: 'get'
  })
}

// 新增供应商类型管理
export function addType(data) {
  return request({
    url: '/supplier/type',
    method: 'post',
    data: data
  })
}

// 修改供应商类型管理
export function updateType(data) {
  return request({
    url: '/supplier/type',
    method: 'put',
    data: data
  })
}

// 删除供应商类型管理
export function delType(id) {
  return request({
    url: '/supplier/type/' + id,
    method: 'delete'
  })
}
