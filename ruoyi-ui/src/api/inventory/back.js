import request from '@/utils/request'

// 查询材料退库列表
export function listBack(query) {
  return request({
    url: '/inventory/back/list',
    method: 'get',
    params: query
  })
}

// 查询材料退库详细
export function getBack(backId) {
  return request({
    url: '/inventory/back/' + backId,
    method: 'get'
  })
}

// 新增材料退库
export function addBack(data) {
  return request({
    url: '/inventory/back',
    method: 'post',
    data: data
  })
}

// 修改材料退库
export function updateBack(data) {
  return request({
    url: '/inventory/back',
    method: 'put',
    data: data
  })
}

// 删除材料退库
export function delBack(backId) {
  return request({
    url: '/inventory/back/' + backId,
    method: 'delete'
  })
}
