import request from '@/utils/request'

// 查询材料总计划列表
export function listPlan(query) {
  return request({
    url: '/purchase/plan/list',
    method: 'get',
    params: query
  })
}

// 查询材料总计划详细
export function getPlan(planId) {
  return request({
    url: '/purchase/plan/' + planId,
    method: 'get'
  })
}

// 新增材料总计划
export function addPlan(data) {
  return request({
    url: '/purchase/plan',
    method: 'post',
    data: data
  })
}

// 修改材料总计划
export function updatePlan(data) {
  return request({
    url: '/purchase/plan',
    method: 'put',
    data: data
  })
}

// 删除材料总计划
export function delPlan(planId) {
  return request({
    url: '/purchase/plan/' + planId,
    method: 'delete'
  })
}
