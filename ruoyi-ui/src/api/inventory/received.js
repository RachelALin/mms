import request from '@/utils/request'

// 查询材料入库列表
export function listReceived(query) {
  return request({
    url: '/inventory/received/list',
    method: 'get',
    params: query
  })
}

// 查询材料入库详细
export function getReceived(inId) {
  return request({
    url: '/inventory/received/' + inId,
    method: 'get'
  })
}

// 新增材料入库
export function addReceived(data) {
  return request({
    url: '/inventory/received',
    method: 'post',
    data: data
  })
}

// 修改材料入库
export function updateReceived(data) {
  return request({
    url: '/inventory/received',
    method: 'put',
    data: data
  })
}

// 删除材料入库
export function delReceived(inId) {
  return request({
    url: '/inventory/received/' + inId,
    method: 'delete'
  })
}
