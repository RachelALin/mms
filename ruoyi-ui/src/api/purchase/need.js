import request from '@/utils/request'

// 查询材料需用计划列表
export function listNeed(query) {
  return request({
    url: '/purchase/need/list',
    method: 'get',
    params: query
  })
}

// 查询材料需用计划详细
export function getNeed(needId) {
  return request({
    url: '/purchase/need/' + needId,
    method: 'get'
  })
}

// 新增材料需用计划
export function addNeed(data) {
  return request({
    url: '/purchase/need',
    method: 'post',
    data: data
  })
}

// 修改材料需用计划
export function updateNeed(data) {
  return request({
    url: '/purchase/need',
    method: 'put',
    data: data
  })
}

// 删除材料需用计划
export function delNeed(needId) {
  return request({
    url: '/purchase/need/' + needId,
    method: 'delete'
  })
}
