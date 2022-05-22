import request from '@/utils/request'

// 查询材料调拨列表
export function listChange(query) {
  return request({
    url: '/inventory/change/list',
    method: 'get',
    params: query
  })
}

// 查询材料调拨详细
export function getChange(chaId) {
  return request({
    url: '/inventory/change/' + chaId,
    method: 'get'
  })
}

// 新增材料调拨
export function addChange(data) {
  return request({
    url: '/inventory/change',
    method: 'post',
    data: data
  })
}

// 修改材料调拨
export function updateChange(data) {
  return request({
    url: '/inventory/change',
    method: 'put',
    data: data
  })
}

// 删除材料调拨
export function delChange(chaId) {
  return request({
    url: '/inventory/change/' + chaId,
    method: 'delete'
  })
}
