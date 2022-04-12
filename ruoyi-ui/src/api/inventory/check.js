import request from '@/utils/request'

// 查询材料盘点列表
export function listCheck(query) {
  return request({
    url: '/inventory/check/list',
    method: 'get',
    params: query
  })
}

// 查询材料盘点详细
export function getCheck(cheId) {
  return request({
    url: '/inventory/check/' + cheId,
    method: 'get'
  })
}

// 新增材料盘点
export function addCheck(data) {
  return request({
    url: '/inventory/check',
    method: 'post',
    data: data
  })
}

// 修改材料盘点
export function updateCheck(data) {
  return request({
    url: '/inventory/check',
    method: 'put',
    data: data
  })
}

// 删除材料盘点
export function delCheck(cheId) {
  return request({
    url: '/inventory/check/' + cheId,
    method: 'delete'
  })
}
