import request from '@/utils/request'

// 查询材料合同列表
export function listContract(query) {
  return request({
    url: '/purchase/contract/list',
    method: 'get',
    params: query
  })
}

// 查询材料合同详细
export function getContract(conId) {
  return request({
    url: '/purchase/contract/' + conId,
    method: 'get'
  })
}

// 新增材料合同
export function addContract(data) {
  return request({
    url: '/purchase/contract',
    method: 'post',
    data: data
  })
}

// 修改材料合同
export function updateContract(data) {
  return request({
    url: '/purchase/contract',
    method: 'put',
    data: data
  })
}

// 删除材料合同
export function delContract(conId) {
  return request({
    url: '/purchase/contract/' + conId,
    method: 'delete'
  })
}
