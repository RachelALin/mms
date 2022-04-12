import request from '@/utils/request'

// 查询材料出库列表
export function listDelivery(query) {
  return request({
    url: '/inventory/delivery/list',
    method: 'get',
    params: query
  })
}

// 查询材料出库详细
export function getDelivery(outId) {
  return request({
    url: '/inventory/delivery/' + outId,
    method: 'get'
  })
}

// 新增材料出库
export function addDelivery(data) {
  return request({
    url: '/inventory/delivery',
    method: 'post',
    data: data
  })
}

// 修改材料出库
export function updateDelivery(data) {
  return request({
    url: '/inventory/delivery',
    method: 'put',
    data: data
  })
}

// 删除材料出库
export function delDelivery(outId) {
  return request({
    url: '/inventory/delivery/' + outId,
    method: 'delete'
  })
}
