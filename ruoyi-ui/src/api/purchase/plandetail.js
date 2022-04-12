import request from '@/utils/request'

// 查询材料与材料总计划列表
export function listPlandetail(query) {
  return request({
    url: '/purchase/plandetail/list',
    method: 'get',
    params: query
  })
}

// 查询材料与材料总计划详细
export function getPlandetail(id) {
  return request({
    url: '/purchase/plandetail/' + id,
    method: 'get'
  })
}

// 新增材料与材料总计划
export function addPlandetail(data) {
  return request({
    url: '/purchase/plandetail',
    method: 'post',
    data: data
  })
}

// 修改材料与材料总计划
export function updatePlandetail(data) {
  return request({
    url: '/purchase/plandetail',
    method: 'put',
    data: data
  })
}

// 删除材料与材料总计划
export function delPlandetail(id) {
  return request({
    url: '/purchase/plandetail/' + id,
    method: 'delete'
  })
}
