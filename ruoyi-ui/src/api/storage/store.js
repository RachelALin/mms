import request from '@/utils/request'

// 查询仓库管理列表
export function listStore(query) {
  return request({
    url: '/storage/store/list',
    method: 'get',
    params: query
  })
}

// 查询仓库管理详细
export function getStore(storeId) {
  return request({
    url: '/storage/store/' + storeId,
    method: 'get'
  })
}
//根据proId查询所有仓库信息
export function getStoreByProId(proId) {
  return request({
    url: '/storage/store/pro/' + proId,
    method: 'get',
    params: proId
  })
}
// 新增仓库管理
export function addStore(data) {
  return request({
    url: '/storage/store',
    method: 'post',
    data: data
  })
}

// 修改仓库管理
export function updateStore(data) {
  return request({
    url: '/storage/store',
    method: 'put',
    data: data
  })
}

// 删除仓库管理
export function delStore(storeId) {
  return request({
    url: '/storage/store/' + storeId,
    method: 'delete'
  })
}



// 材料入库并修改仓库管理
export function updateStoreReceived(data) {
  return request({
    url: '/storage/store/received',
    method: 'put',
    data: data
  })
}